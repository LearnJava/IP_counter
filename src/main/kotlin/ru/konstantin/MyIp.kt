package ru.konstantin

data class MyIp(val first: UByte, val second: UByte, val third: UByte, val fourth: UByte) {

    override fun toString(): String {
        return "$first.$second.$third.$fourth"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MyIp

        if (first != other.first) return false
        if (second != other.second) return false
        if (third != other.third) return false
        if (fourth != other.fourth) return false

        return true
    }

    override fun hashCode(): Int {
        var result = first.hashCode()
        result = 31 * result + second.hashCode()
        result = 31 * result + third.hashCode()
        result = 31 * result + fourth.hashCode()
        return result
    }
}