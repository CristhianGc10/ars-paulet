package com.ars.paulet.presentation.theme

import androidx.compose.ui.graphics.Color

// --- VIBRANT WABI-SABI PALETTE (3rd Revision) ---

// Primary - Richer Wood tones
val WabiWood = Color(0xFF9E7C65)
val WabiWoodLight = Color(0xFFB89E8D)
val WabiWoodDark = Color(0xFF7E5F4C)
val WabiBark = Color(0xFF5C4033) // keep dark for contrast

// Neutral - Paper and sand (Unchanged, foundation of wabi-sabi)
val WabiPaper = Color(0xFFF5F0E6)
val WabiSand = Color(0xFFE8DFD1)
val WabiCream = Color(0xFFFAF7F2)
val WabiLinen = Color(0xFFEDE8E0)

// Grey - Stone tones (Unchanged, for neutrality)
val WabiStone = Color(0xFF9A9590)
val WabiAsh = Color(0xFFB8B4AF)
val WabiSlate = Color(0xFF78746F)
val WabiCharcoal = Color(0xFF4A4744)

// Green - More lively Moss and bamboo
val WabiMoss = Color(0xFF889970)
val WabiBamboo = Color(0xFF9EB08A)
val WabiTea = Color(0xFFAABBAA) // keep it softer for secondary container
val WabiForest = Color(0xFF505D43) // keep dark for contrast

// Accent - Deeper & Richer
val WabiIndigo = Color(0xFF586F9A)
val WabiIndigoLight = Color(0xFF798BB0)
val WabiIndigoDark = Color(0xFF3E507A)
val WabiTerracotta = Color(0xFFE09A6C)
val WabiGold = Color(0xFFD1B370)

// Semantic - Adjusted for vibrancy
val WabiSuccess = Color(0xFF889970) // WabiMoss
val WabiWarning = Color(0xFFD1B370) // WabiGold
val WabiInfo = Color(0xFF586F9A)    // WabiIndigo

// -- Semantic Error Colors (More noticeable) --
val WabiErrorLight = Color(0xFFDB7070)
val WabiOnErrorLight = Color.White
val WabiErrorContainerLight = Color(0xFFF8E2E2)
val WabiOnErrorContainerLight = Color(0xFF5D2F2F)

val WabiErrorDark = Color(0xFFE8AFAF)
val WabiOnErrorDark = Color(0xFF5D2F2F)
val WabiErrorContainerDark = Color(0xFF934F4F)
val WabiOnErrorContainerDark = Color(0xFFF8E2E2)

// Theme Specific Backgrounds/Surfaces (Unchanged)
val WabiBackgroundLight = Color(0xFFFCFAF7)
val WabiSurfaceLight = Color(0xFFF5F2ED)
val WabiOnBackgroundLight = Color(0xFF3D3A36)
val WabiOnSurfaceLight = Color(0xFF4A4744)
val WabiSurfaceVariantLight = WabiLinen
val WabiOnSurfaceVariantLight = WabiSlate

val WabiBackgroundDark = Color(0xFF1A1918)
val WabiSurfaceDark = Color(0xFF252322)
val WabiOnBackgroundDark = Color(0xFFE8E4DE)
val WabiOnSurfaceDark = Color(0xFFDDD8D1)
val WabiSurfaceVariantDark = WabiCharcoal
val WabiOnSurfaceVariantDark = WabiAsh

// Course Level Colors (Adjusted for more vibrancy)
val LevelInicialColor = Color(0xFF9EB08A)   // Lighter Bamboo
val LevelBasico1Color = Color(0xFF798BB0)   // Lighter Indigo
val LevelBasico2Color = Color(0xFFE09A6C)   // Terracotta

// Activity Colors
val ActivityListeningColor = WabiInfo
val ActivityConversationColor = WabiSuccess
val ActivityReadingColor = WabiGold
val ActivityWritingColor = WabiErrorLight

// Progress Bar Colors
val ProgressFilledColor = WabiMoss
val ProgressEmptyColor = WabiLinen
