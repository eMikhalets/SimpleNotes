package com.emikhalets.simplenotes.domain.entities

import androidx.compose.ui.graphics.vector.ImageVector

data class TopBarActionEntity(
    val imageVector: ImageVector,
    val onClick: () -> Unit,
)
