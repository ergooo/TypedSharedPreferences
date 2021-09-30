package jp.co.ergo.android.persistence

import androidx.preference.PreferenceManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

sealed class Keys {
    object Name : Key<String>
    object Age : Key<Int>
    object Birthday : Key<Long>
    object SyncRate : Key<Float>
    object HasIdCard : Key<Boolean>
    object Friends : Key<Set<String>>
}

@RunWith(AndroidJUnit4::class)
class TypedSharedPreferencesTest : TestCase() {
    private val preferences =
        PreferenceManager.getDefaultSharedPreferences(InstrumentationRegistry.getInstrumentation().targetContext)

    @Before
    fun setup() {
        preferences.edit().clear().commit()
    }

    @Test
    fun test() {
        val sut = TypedSharedPreferences(preferences)
        sut.putString(Entry(Keys.Name, "Shinji"))
        sut.putInt(Entry(Keys.Age, 14))
        sut.putLong(Entry(Keys.Birthday, 991753200))
        sut.putFloat(Entry(Keys.SyncRate, 41.3f))
        sut.putBoolean(Entry(Keys.HasIdCard, true))
        sut.putStringSet(Entry(Keys.Friends, setOf("Rei", "Asuka")))

        assertThat(sut.getString(Keys.Name, null), `is`("Shinji"))
        assertThat(sut.getInt(Keys.Age, 0), `is`(14))
        assertThat(sut.getLong(Keys.Birthday, 0), `is`(991753200))
        assertThat(sut.getFloat(Keys.SyncRate, 0f), `is`(41.3f))
        assertThat(sut.getBoolean(Keys.HasIdCard, false), `is`(false))
        assertThat(sut.getStringSet(Keys.Friends, null), `is`(setOf("Rei", "Asuka")))
    }
}
