# TypedSharedPreferences

By specifying type parameters for the keys you define, you can avoid typing mistakes when saving to `SharedPreferences`.

## Installation

```
implementation 'io.github.ergooo:TypedSharedPreferences:0.0.2'
```

## Code examples

Define keys with type parameter.

```kotlin
sealed class Keys {
    object Name : Key<String>
    object Age : Key<Int>
    object Birthday : Key<Long>
    object SyncRate : Key<Float>
    object HasIdCard : Key<Boolean>
    object Friends : Key<Set<String>>
}
```

You can pass key / value pairs as `Entry`.

```kotlin
val typedSharedPreferences = TypedSharedPreferences(preferences)
typedSharedPreferences.putString(Entry(Keys.Name, "Shinji"))
```

If you specify a value other than String for `Keys.Name`, the compiler will tell you an error.
If you specify a key with a non-String parameter (such as `Keys.Age`) as the parameter for the `putString()` or `getString()`, compiler will tell you an error.
