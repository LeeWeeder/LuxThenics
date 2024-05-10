package com.leeweeder.luxthenics.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.leeweeder.luxthenics.R

val materialSymbol = FontFamily(Font(resId = R.font.material_symbol))

val Typography = Typography()

val Typography.timer: TextStyle
    get() = displaySmall.copy(fontFamily = FontFamily.Monospace)
