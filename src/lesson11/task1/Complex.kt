@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import kotlinx.html.Entities
import lesson1.task1.sqr
import java.util.Collections.max

/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */

/**
 * функция "constructor-like" из строки вида x+yi
 */
fun Complex(s: String): Complex {
    var re = 0.0
    var im = 0.0
    val s1 = Regex("""\s""").replace(s, "")
    val sRegex = Regex("""(-?\d+(?:\.\d+)?)?(?:([-+]\d+(?:\.\d+)?)i)?""").matchEntire(s1)?.groupValues
    if ((sRegex == null)) throw IllegalStateException()
    if (sRegex[1] != "") re = sRegex[1].toDouble()
    if (sRegex[2] != "") im = sRegex[2].toDouble()
    return Complex(re, im)
}



class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)


    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(re + other.re, im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus() = Complex(-re, -im)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(re - other.re, im - other.im)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex = Complex(
        re * other.re - im * other.im,
        re * other.im + im * other.re
    )

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex = Complex(
        (re * other.re + im * other.im) / (sqr(other.re) + sqr(other.im)),
        (im * other.re - re * other.im) / (sqr(other.re) + sqr(other.im))
    )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Complex && re == other.re && im == other.im

    /**
     * Преобразование в строку
     */
    override fun toString(): String = when {
        im > 0 -> "$re+${im}i"
        im < 0 -> "$re${im}i"
        else -> "$re"
    }

    override fun hashCode(): Int {
        var res = re.hashCode()
        res = res * 31 + im.hashCode()
        return res
    }
}
