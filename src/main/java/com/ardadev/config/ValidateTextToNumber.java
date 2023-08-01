package com.ardadev.config;

import com.ardadev.domain.entities.country.Country;
import com.ardadev.domain.entities.currency_converted.CurrencyConverted;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateTextToNumber implements KeyListener {
    private final JTextField input;
    private final JTextField output;
    private final CurrencyConverted currencyConverted;
    private final Country country;
    private static final String REGEX_DOUBLE = "^(\\d+\\.?(\\d{1,2})?)$";
    private static final String REGEX_DOUBLE_FIRST_ZERO = "^(\\d\\.(\\d{1,2})?)$";
    public ValidateTextToNumber(JTextField input, JTextField output, CurrencyConverted currencyConverted, Country country) {
        this.input = input;
        this.output = output;
        this.currencyConverted = currencyConverted;
        this.country = country;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!validateInput(c))
            e.consume();
    }

    /**
     * This function disabled the copied some character to clipboard (CTRL + C).
     * @param e Key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_V) &&
                ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            e.consume();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!(input.getText().isEmpty() || e.getKeyChar() == '.')){
            double currencyChange = currencyConverted.getConversion_rates().get(country.getCurrencyCode());
            double change = Double.parseDouble(input.getText()) * currencyChange;
            SwingUtilities.invokeLater(() -> {
                output.setText(String.valueOf((Math.round(change * 100.0) / 100.0)));
            });
        }
    }

    public Boolean validateInput(char character) {

        String REGEX;
        String inputText = this.input.getText();

        System.out.println(inputText);

        if (Objects.equals(inputText, "0")) {
            REGEX = REGEX_DOUBLE_FIRST_ZERO;
        } else {
            REGEX = REGEX_DOUBLE;
        }
        inputText = this.input.getText() + character;
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(inputText);

        return matcher.find();
    }
}

/**
 * Expresiones Regulares
 * Metacaracteres: <([{\^-=$!|]})?*+.> se vuelven caracteres ordinarios empezando con un backslash
 * O entre \Q y \E
 *
 * Clases de caracteres
 *
 * Construct	Description
 * [abc]	a, b, or c (simple class)
 * [^abc]	Any character except a, b, or c (negation)
 * [a-zA-Z]	a through z, or A through Z, inclusive (range)
 * [a-d[m-p]]	a through d, or m through p: [a-dm-p] (union)
 * [a-z&&[def]]	d, e, or f (intersection)
 * [a-z&&[^bc]]	a through z, except for b and c: [ad-z] (subtraction)
 * [a-z&&[^m-p]]	a through z, and not m through p: [a-lq-z] (subtraction)
 *
 * Clases de caracteres predefinidos
 *
 * Construct	Description
 * .	Any character (may or may not match line terminators)
 * \d	A digit: [0-9]
 * \D	A non-digit: [^0-9]
 * \s	A whitespace character: [ \t\n\x0B\f\r]
 * \S	A non-whitespace character: [^\s]
 * \w	A word character: [a-zA-Z_0-9]
 * \W	A non-word character: [^\w]
 *
 * Cuantificadores
 *
 *  Greedy	Reluctant	Possessive	Meaning
 *  X?	X??	X?+	X, once or not at all
 *  X*	X*?	X*+	X, zero or more times
 *  X+	X+?	X++	X, one or more times
 *  X{n}	X{n}?	X{n}+	X, exactly n times
 *  X{n,}	X{n,}?	X{n,}+	X, at least n times
 *  X{n,m}	X{n,m}?	X{n,m}+	X, at least n but not more than m times
 *
 * Enter your regex: .*foo  // greedy quantifier
 * Enter input string to search: xfooxxxxxxfoo
 * I found the text "xfooxxxxxxfoo" starting at index 0 and ending at index 13.
 *
 * Enter your regex: .*?foo  // reluctant quantifier
 * Enter input string to search: xfooxxxxxxfoo
 * I found the text "xfoo" starting at index 0 and ending at index 4.
 * I found the text "xxxxxxfoo" starting at index 4 and ending at index 13.
 *
 * Enter your regex: .*+foo // possessive quantifier
 * Enter input string to search: xfooxxxxxxfoo
 * No match found.
 *
 * Group's number: ((A)(B(C))),  the groups are numbered by counting their opening parentheses from left to right.
 * 1 ((A)(B(C)))
 * 2 (A)
 * 3 (B(C))
 * 4 (C)
 *
 * Back-reference: (\d\d)\1, \1 selected the number of group for reuse
 *
 * Comparadores con límites
 *
 *  Boundary Construct	Description
 *  ^	The beginning of a line
 *  $	The end of a line
 *  \b	A word boundary
 *  \B	A non-word boundary
 *  \A	The beginning of the input
 *  \G	The end of the previous match
 *  \Z	The end of the input but for the final terminator, if any
 *  \z	The end of the input
 *
 *  Expresiones de banmdera incrustadas
 *
 *  Constant	Equivalent Embedded Flag Expression
 *  Pattern.CANON_EQ	None
 *  Pattern.CASE_INSENSITIVE	(?i)
 *  Pattern.COMMENTS	(?x)
 *  Pattern.MULTILINE	(?m)
 *  Pattern.DOTALL	(?s)
 *  Pattern.LITERAL	None
 *  Pattern.UNICODE_CASE	(?u)
 *  Pattern.UNIX_LINES	(?d)
 *
 *  Apliccacion en un split:
 *
 * import java.util.regex.Pattern;
 * import java.util.regex.Matcher;
 *
 * public class SplitDemo {
 *
 *     private static final String REGEX = ":";
 *     private static final String INPUT =
 *         "one:two:three:four:five";
 *
 *     public static void main(String[] args) {
 *         Pattern p = Pattern.compile(REGEX);
 *         String[] items = p.split(INPUT);
 *         for(String s : items) {
 *             System.out.println(s);
 *         }
 *     }
 * }
 * OUTPUT:
 *
 * one
 * two
 * three
 * four
 * five
 *
 */
