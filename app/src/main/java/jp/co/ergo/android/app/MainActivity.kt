package jp.co.ergo.android.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import jp.co.ergo.android.persistence.Entry
import jp.co.ergo.android.persistence.Key
import jp.co.ergo.android.persistence.TypedSharedPreferences

sealed class Keys {
    object Name : Key<String>
    object Age : Key<Int>
    object Birthday : Key<Long>
    object SyncRate : Key<Float>
    object HasIdCard : Key<Boolean>
    object Friends : Key<Set<String>>
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val typedSharedPreferences =
            TypedSharedPreferences(PreferenceManager.getDefaultSharedPreferences(this))
        findViewById<Button>(R.id.button).setOnClickListener {
            typedSharedPreferences.putString(Entry(Keys.Name, "Shinji"))
            typedSharedPreferences.putInt(Entry(Keys.Age, 14))
            typedSharedPreferences.putLong(Entry(Keys.Birthday, 991753200))
            typedSharedPreferences.putFloat(Entry(Keys.SyncRate, 41.3f))
            typedSharedPreferences.putBoolean(Entry(Keys.HasIdCard, true))
            typedSharedPreferences.putStringSet(Entry(Keys.Friends, setOf("Rei", "Asuka")))

           show()
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(this).edit().clear().apply()
            show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun show() {
        val typedSharedPreferences =
            TypedSharedPreferences(PreferenceManager.getDefaultSharedPreferences(this))
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = """
                ${typedSharedPreferences.getString(Keys.Name, null)}
                ${typedSharedPreferences.getInt(Keys.Age, 0)}
                ${typedSharedPreferences.getLong(Keys.Birthday, 0)}
                ${typedSharedPreferences.getFloat(Keys.SyncRate, 0f)}
                ${typedSharedPreferences.getBoolean(Keys.HasIdCard, false)}
                ${typedSharedPreferences.getStringSet(Keys.Friends, null)}
            """.trimIndent()
    }
}
