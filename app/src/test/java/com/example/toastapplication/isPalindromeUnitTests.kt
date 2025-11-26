package com.example.ispalindrome

import org.junit.Test

import org.junit.Assert.*


class IsPalindromeUnitTests {
    @Test
    fun `isPalindrome() for simple even-length string`() {
        assertEquals(true, checkPalindrome("racecar"))
    }

    @Test
    fun `isPalindrome() for simple odd-length string`() {
        assertEquals(true, checkPalindrome("mom"))
    }

    @Test
    fun `isPalindrome() for simple non-palindrome`() {
        assertEquals(false, checkPalindrome("banana"))
    }

    // boundary cases
    @Test
    fun `isPalindrome() for empty string`() {
        assertEquals(true, checkPalindrome(""))
    }

    @Test
    fun `isPalindrome() for palindrome with single char`() {
        assertEquals(true, checkPalindrome("a"))
    }

    // casing, non-alphanumeric chars
    @Test
    fun `isPalindrome() for string with uppercasing`() {
        assertEquals(true, checkPalindrome("rAceCar"))
    }

    @Test
    fun `isPalindrome() for string with spacing and padding`() {
        assertEquals(true, checkPalindrome("   race car "))
    }

    @Test
    fun `isPalindrome() for string with punctuation`() {
        assertEquals(true, checkPalindrome("A man, a plan, a canal: Panama"))
    }

    @Test
    fun `isPalindrome() for string with numbers`() {
        assertEquals(true, checkPalindrome("12321"))
    }


    // edge cases
    // null case - isPalindrome should never see a null value
    @Test
    fun `isPalindrome() for string with only spaces and punctuation`() {
        assertEquals(true, checkPalindrome("., ./"))
    }

    @Test
    fun `isPalindrome() for string using extended chars`() {
        assertEquals(false, checkPalindrome("madám"))
    }

    @Test
    fun `isPalindrome() for string using extended chars and is palindrome`() {
        assertEquals(true, checkPalindrome("mádám"))
    }
}