package ru.avito.avitotest.core

import android.content.Context
import android.view.View

interface AdapterCallbacks<DATA, B> {

    fun bindViews(binding: B, item: DATA, context: Context)

    fun onViewClicked(view: View, item: DATA) {}
}