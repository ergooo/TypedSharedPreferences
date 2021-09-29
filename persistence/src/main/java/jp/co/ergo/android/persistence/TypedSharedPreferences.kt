package jp.co.ergo.android.persistence

import android.content.SharedPreferences

interface Key<T> {
    val name: String
        get() = this::class.java.simpleName
}

class Entry<V>(val key: Key<V>, val value: V)

class TypedSharedPreferences(private val sharedPreferences: SharedPreferences) {
    fun putString(value: Entry<String>) {
        sharedPreferences.edit().putString(value.key.name, value.value).apply()
    }

    fun putInt(value: Entry<Int>) {
        sharedPreferences.edit().putInt(value.key.name, value.value).apply()
    }

    fun putFloat(value: Entry<Float>) {
        sharedPreferences.edit().putFloat(value.key.name, value.value).apply()
    }

    fun putLong(value: Entry<Long>) {
        sharedPreferences.edit().putLong(value.key.name, value.value).apply()
    }

    fun putBoolean(value: Entry<Boolean>) {
        sharedPreferences.edit().putBoolean(value.key.name, value.value).apply()
    }

    fun putStringSet(value: Entry<Set<String>>) {
        sharedPreferences.edit().putStringSet(value.key.name, value.value).apply()
    }

    fun getString(key: Key<String>, defValue: String?): String? {
        return sharedPreferences.getString(key.name, defValue)
    }

    fun getInt(key: Key<Int>, defValue: Int): Int {
        return sharedPreferences.getInt(key.name, defValue)
    }

    fun getFloat(key: Key<Float>, defValue: Float): Float {
        return sharedPreferences.getFloat(key.name, defValue)
    }

    fun getLong(key: Key<Long>, defValue: Long): Long {
        return sharedPreferences.getLong(key.name, defValue)
    }

    fun getBoolean(key: Key<Boolean>, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key.name, defValue)
    }

    fun getStringSet(key: Key<Set<String>>, defValue: Set<String>?): Set<String>? {
        return sharedPreferences.getStringSet(key.name, defValue)
    }
}
