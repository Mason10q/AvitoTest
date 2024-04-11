package ru.avito.avitotest.core

import android.content.Context
import android.content.res.Resources
import android.view.View

interface AdapterCallbacks<DATA, B> {

    fun bindViews(binding: B, item: DATA)

    fun onViewClicked(view: View, item: DATA, position: Int) {}
}