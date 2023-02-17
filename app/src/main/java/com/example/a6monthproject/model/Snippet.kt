package com.example.a6monthproject.model


data class Snippet(
    var channelId: String?, // UCdKuE7a2QZeHPhDntXVZ91w
    var channelTitle: String?, // Kuplinov ► Play
    var description: String?,
    var localized: Localized?,
    var publishedAt: String?, // 2023-01-11 09:20:27
    var thumbnails: Thumbnails?,
    var resourceId: ResourceId?,
    var title: String? // Choo-Choo Charles Прохождение
)

data class ResourceId(
    var kind: String?,
    var videoId: String?
)
