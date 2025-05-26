package com.example.ui.util


import android.util.Base64
import org.json.JSONObject

object TokenStore {
    private var token: String? = null
    private var role: String? = null

    fun saveToken(jwt: String) {
        token = jwt
        role = extractRole(jwt)
    }

    fun clear() {
        token = null
        role = null
    }

    fun getToken(): String? = token
    fun getRole(): String? = role

    private fun extractRole(jwt: String): String? {
        return try {
            val parts = jwt.split(".")
            if (parts.size == 3) {
                val payload = String(Base64.decode(parts[1], Base64.URL_SAFE))
                val json = JSONObject(payload)
                json.getString("role") // payload должен содержать ключ "role"
            } else null
        } catch (e: Exception) {
            null
        }
    }
}
