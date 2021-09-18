package com.example.youtubeapi.model

data class PlayListJs(
    val etag: String, // O4Gj5BQ8T25FCYzthPckdkfE5QE
    val items: List<Item>,
    val kind: String, // youtube#playlistListResponse
    val nextPageToken: String, // CAUQAA
    val pageInfo: PageInfo
) {
    data class Item(
        val contentDetails: ContentDetails,
        val etag: String, // qe0g-E6pHQEdejBgnySmHjbAHpg
        val id: String, // PLMFUaRAk0WsbJZuURpDP4_ISBuw5UvJXq
        val kind: String, // youtube#playlist
        val snippet: Snippet
    ) {
        data class ContentDetails(
            val itemCount: Int // 11
        )

        data class Snippet(
            val channelId: String, // UCqk3CdGN_j8IR9z4uBbVPSg
            val channelTitle: String, // Lana Del Rey
            val description: String,
            val localized: Localized,
            val publishedAt: String, // 2021-03-19T16:22:56Z
            val thumbnails: Thumbnails,
            val title: String // LANA DEL REY - CHEMTRAILS OVER THE COUNTRY CLUB
        ) {
            data class Localized(
                val description: String,
                val title: String // LANA DEL REY - CHEMTRAILS OVER THE COUNTRY CLUB
            )

            data class Thumbnails(
                val default: Default,
                val high: High,
                val maxres: Maxres,
                val medium: Medium,
                val standard: Standard
            ) {
                data class Default(
                    val height: Int, // 90
                    val url: String, // https://i.ytimg.com/vi/vBHild0PiTE/default.jpg
                    val width: Int // 120
                )

                data class High(
                    val height: Int, // 360
                    val url: String, // https://i.ytimg.com/vi/vBHild0PiTE/hqdefault.jpg
                    val width: Int // 480
                )

                data class Maxres(
                    val height: Int, // 720
                    val url: String, // https://i.ytimg.com/vi/vBHild0PiTE/maxresdefault.jpg
                    val width: Int // 1280
                )

                data class Medium(
                    val height: Int, // 180
                    val url: String, // https://i.ytimg.com/vi/vBHild0PiTE/mqdefault.jpg
                    val width: Int // 320
                )

                data class Standard(
                    val height: Int, // 480
                    val url: String, // https://i.ytimg.com/vi/vBHild0PiTE/sddefault.jpg
                    val width: Int // 640
                )
            }
        }
    }

    data class PageInfo(
        val resultsPerPage: Int, // 5
        val totalResults: Int // 10
    )
}