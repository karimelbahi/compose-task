package common

fun String?.convertNullToEmpty(): String {
    return this?.replace("null","") ?: ""
}
