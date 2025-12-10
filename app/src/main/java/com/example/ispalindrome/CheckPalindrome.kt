package com.example.ispalindrome

fun checkPalindrome(s: String): Boolean {
    val stripInput = s.trim().filter { c -> c.isLetterOrDigit() }.lowercase()
    return if (stripInput.isEmpty()) {
        return true
    } else if (stripInput.length % 2 == 0) {
        stripInput.substring(0, stripInput.length / 2) == stripInput.substring(stripInput.length / 2).reversed()
    } else {
        stripInput.substring(0, stripInput.length / 2) == stripInput.substring((stripInput.length / 2) + 1).reversed()
    }
}

// https://leetcode.com/problems/valid-palindrome/description/
private fun checkPalindrome2(s: String): Boolean {
    val filteredS = s.filter { c -> c.isLetterOrDigit() }.lowercase()
    return filteredS == filteredS.reversed()
}