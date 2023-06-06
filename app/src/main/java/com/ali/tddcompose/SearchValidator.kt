package com.ali.tddcompose

class  SearchValidator {
    fun validate(query: String): Boolean {
        return query.isNotEmpty() && query.length >= 3
    }

}
