package com.example.groceryapp.welcome_login_singup_screens.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.favorites.repository.FavoritesRepository
import com.example.groceryapp.repository.GoodRepository
import com.example.groceryapp.welcome_login_singup_screens.data.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

// Состояния для UI
sealed class UiState<out T> {
    object Idle : UiState<Nothing>()   // Idle - ничего
    object Loading : UiState<Nothing>() // Загрузка
    data class Success<out T>(val data: T) : UiState<T>() // Успех
    data class Error(val message: String) : UiState<Nothing>() // Ошибка
}


@HiltViewModel
class FirebaseViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val favRepository: FavoritesRepository
) : ViewModel() {
    // Переменные для хранения ввода пользователя
    val emailInput = MutableStateFlow("")
    val phoneInput = MutableStateFlow("")
    val passwordInput = MutableStateFlow("")
    // Функции для обновления текста из Compose экрана
    fun onEmailChange(newValue: String) { emailInput.value = newValue }

    fun onPhoneChange(newValue: String) { phoneInput.value = newValue }

    fun onPasswordChange(newValue: String) { passwordInput.value = newValue }

    private val _userState = MutableStateFlow<UiState<UserProfile>>(UiState.Idle)

    val userState: StateFlow<UiState<UserProfile>> = _userState
///////////////////////////////////////////////////////////////////////////////////////////
    // Вход в аккаунт
    fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { signIn ->
                if (signIn.isSuccessful) {
                    val user = UserProfile(
                        email = email,
                    )
                    _userState.value = UiState.Success(user)
                } else {
                    _userState.value = UiState.Error("Не удалось войти")
                }
            }
    }
///////////////////////////////////////////////////////////////////////////////////////////
    // Выход из аккаунта
    fun signOut(
        onSuccess: () -> Unit = {}
    ) {
        viewModelScope.launch {
            val currentUid = auth.currentUser?.uid
            if (currentUid != null) {
                favRepository.clearFavorites(currentUid)
            }
            auth.signOut()
            onSuccess()
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////
    // Регистрация пользователя (Auth + создание документа в Firestore)
    fun registerUser(email: String, password: String, phone: String) {
        if (email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            _userState.value = UiState.Error("Заполните все поля")
            return
        }
// 1. Проверка формата Email
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (!email.matches(emailPattern.toRegex())) {
            _userState.value = UiState.Error("Введите корректный Email")
            return
        }
// 2. Проверка длины и сложности пароля
        if (password.length < 6) {
            _userState.value = UiState.Error("Пароль должен быть не менее 6 символов")
            return
        }
        if (!password.any { it.isDigit() }) {
            _userState.value = UiState.Error("Пароль должен содержать хотя бы одну цифру")
            return
        }

// 3. Проверка формата номера телефона (пример для СНГ: +7 или 8 и 10 цифр)
// Очищаем строку от масок (пробелов, скобок, дефисов), если они есть
        val cleanPhone = phone.replace(Regex("[^0-9+]"), "")
        if (cleanPhone.length < 11) {
            _userState.value = UiState.Error("Введите корректный номер телефона")
            return
        }

        _userState.value = UiState.Loading

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                val uid = authResult.user?.uid
                if (uid != null) {
                    val newUser = UserProfile(
                        userId = uid,
                        email = email,
                        phone = phone
                    )

                    db.collection("users").document(uid).set(newUser)
                        .addOnSuccessListener {
                            _userState.value = UiState.Success(newUser)
                        }
                        .addOnFailureListener { exception ->
                            _userState.value =
                                UiState.Error("Ошибка регистрации: ${exception.localizedMessage}")
                        }
                }
            }
    }

    // Дозаполнение личных данных (Имя, адрес, город и т.д.)

    fun updateAdditionalInfo(
        name: String,
        address: String,
        zipCode: String,
        city: String,
        country: String
    ) {
        val uid = auth.currentUser?.uid

        if (uid == null) {
            _userState.value = UiState.Error("Пользователь не авторизован")
            return
        }
        _userState.value = UiState.Loading

        val updates = mapOf(
            "name" to name,
            "address" to address,
            "zipCode" to zipCode,
            "city" to city,
            "country" to country
        )

        db.collection("users").document(uid).update(updates)
            .addOnSuccessListener {
                fetchUserProfile()
            }
            .addOnFailureListener { exception ->
                _userState.value = UiState.Error("Ошибка обновления: ${exception.localizedMessage}")
            }

    }
    // Сохранение аватарки (Bitmap -> Base64 -> Firestore)


    fun uploadAvatar(resId: Int, context: Context) {
        try {
            // Конвертируем Int ID в объект Bitmap
            val bitmap = BitmapFactory.decodeResource(context.resources, resId)
            if (bitmap != null) {
                uploadAvatar(bitmap) // Вызываем вашу исходную функцию
            } else {
                _userState.value = UiState.Error("Не удалось загрузить встроенную картинку")
            }
        } catch (e: Exception) {
            _userState.value = UiState.Error("Ошибка обработки ресурса: ${e.localizedMessage}")
        }
    }


    fun uploadAvatar(bitmap: Bitmap) {
        val uid = auth.currentUser?.uid

        if (uid == null) {
            _userState.value = UiState.Error("Пользователь не авторизован")
            return
        }

        _userState.value = UiState.Loading

        val base64String = bitmapToBase64(bitmap)

        db.collection("users").document(uid).update("photoBase64", base64String)
            .addOnSuccessListener {
                fetchUserProfile()
            }
            .addOnFailureListener { exception ->
                _userState.value =
                    UiState.Error("Ошибка сохранения фото: ${exception.localizedMessage}")
            }
    }

    // Метод получения актуальных данных профиля

    fun fetchUserProfile() {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            _userState.value = UiState.Error("Пользователь не авторизован")
            return
        }

        _userState.value = UiState.Loading

        db.collection("users").document(uid).get()
            .addOnSuccessListener { documentSnapshot ->
                val profile = documentSnapshot.toObject(UserProfile::class.java)
                if (profile != null) {
                    _userState.value = UiState.Success(profile)
                } else {
                    _userState.value = UiState.Error("Данные пользователя не найдены")
                }
            }
            .addOnFailureListener { exception ->
                _userState.value =
                    UiState.Error("Ошибка чтения профиля: ${exception.localizedMessage}")
            }

    }

    // Утилиты для сжатия и конвертации изображений

    private fun bitmapToBase64(bitmap: Bitmap): String {
        // 1. Изменяем размер до 400x400
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 400, 400, true)
        val byteArrayOutputStream = ByteArrayOutputStream()

        // 2. Сжимаем в JPEG (качество 70% даст отличный баланс размера и картинки)
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream)

        // Очищаем память от scaledBitmap, если это не тот же самый исходный bitmap
        if (scaledBitmap != bitmap) {
            scaledBitmap.recycle()
        }

        val byteArray = byteArrayOutputStream.toByteArray()

        // 3. Используем NO_WRAP вместо DEFAULT
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }

    fun base64ToBitmap(base64String: String): Bitmap? {
        return try {
            val decodedBytes = Base64.decode(base64String, Base64.NO_WRAP)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: IllegalArgumentException) {
            null // Защита от краша, если строка Base64 повреждена
        }
    }
}
