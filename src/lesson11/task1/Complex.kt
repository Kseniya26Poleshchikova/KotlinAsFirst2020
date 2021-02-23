@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

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

class Complex(var re: Double, var im: Double) {


    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Конструктор из строки вида x+yi
     */
    constructor(s: String) : this(re = 0.0, im = 0.0) {
        when {
            s.matches(Regex("""^-?\d+(\.\d+)*$""")) -> re = s.toDouble()
            s.matches(Regex("""^-?\d+(\.\d+)*i$""")) -> im = s.substring(0..s.lastIndex - 1).toDouble()
            s.matches(Regex("""^-?\d+(\.\d+)*[-+]\d+(\.\d+)*i$""")) -> {
                val j = s.indexOf('+', 1)
                val z = s.indexOf('-', 1)
                val i = if (j > z) j else z
                re = s.substring(0.. i - 1).toDouble()
                im = s.substring(i.. s.lastIndex - 1).toDouble()
            } else -> throw IllegalStateException()
        }
    }

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
    operator fun times(other: Complex): Complex = Complex(re * other.re - im * other.im,
        re * other.im + im * other.re)

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex = Complex((re * other.re + im * other.im) / (sqr(other.re) + sqr(other.im)),
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
