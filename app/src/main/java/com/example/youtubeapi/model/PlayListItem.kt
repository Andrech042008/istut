package com.example.youtubeapi.model

data class PlayListItem(
    val etag: String, // VeemY5-2kr4OMC1Clcly1DvkcoM
    val items: ArrayList<Item>,
    val kind: String, // youtube#playlistItemListResponse
    val nextPageToken: String, // EAAaBlBUOkNBVQ
    val pageInfo: PageInfo
) {
    data class Item(
        val contentDetails: ContentDetails,
        val etag: String, // hAIEhqSx5ey9ypEKwWr9jaO8V68
        val id: String, // UExNRlVhUkFrMFdzYmZuTkhUWE5McWFNWVlPWUplVHNBVS41NkI0NEY2RDEwNTU3Q0M2
        val kind: String, // youtube#playlistItem
        val snippet: Snippet,
    ) {
        data class ContentDetails(
            val videoId: String, // Bag1gUxuU0g
            val videoPublishedAt: String // 2011-12-14T20:48:50Z
        )

        data class Snippet(
            val channelId: String, // UCqk3CdGN_j8IR9z4uBbVPSg
            val channelTitle: String, // Lana Del Rey
            val description: String, // New single High By The BeachListen on Youtube: http://lanadel.re/HBTByoutubeDownload on iTunes: http://lanadel.re/HBTBiTfbStream on Spotify: http://lanadel.re/HBTBspotify More Lana Del Rey:http://lanadelrey.comhttp://www.facebook.com/lanadelreyhttp://www.twitter.com/lanadelreyhttp://lanadelrey.tumblr.comhttp://www.instagram.com/lanadelreyhttp://www.google.com/+lanadelrey#LanaDelRey #BornToDie #vevo #alternative #vevoofficial
            val playlistId: String, // PLMFUaRAk0WsbfnNHTXNLqaMYYOYJeTsAU
            val position: Int, // 0
            val publishedAt: String, // 2021-01-22T19:07:29Z
            val resourceId: ResourceId,
            val thumbnails: Thumbnails,
            val title: String, // Lana Del Rey - Born To Die (Official Music Video)
            val videoOwnerChannelId: String, // UC3N5y6UWKJaKqoU2b_0MfTQ
            val videoOwnerChannelTitle: String // LanaDelReyVEVO
        ) {
            data class ResourceId(
                val kind: String, // youtube#video
                val videoId: String // Bag1gUxuU0g
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
                    val url: String, // https://i.ytimg.com/vi/Bag1gUxuU0g/default.jpg
                    val width: Int // 120
                )

                data class High(
                    val height: Int, // 360
                    val url: String, // https://i.ytimg.com/vi/Bag1gUxuU0g/hqdefault.jpg
                    val width: Int // 480
                )

                data class Maxres(
                    val height: Int, // 720
                    val url: String, // https://i.ytimg.com/vi/Bag1gUxuU0g/maxresdefault.jpg
                    val width: Int // 1280
                )

                data class Medium(
                    val height: Int, // 180
                    val url: String, // https://i.ytimg.com/vi/Bag1gUxuU0g/mqdefault.jpg
                    val width: Int // 320
                )

                data class Standard(
                    val height: Int, // 480
                    val url: String, // https://i.ytimg.com/vi/Bag1gUxuU0g/sddefault.jpg
                    val width: Int // 640
                )
            }
        }
    }

    data class PageInfo(
        val resultsPerPage: Int, // 5
        val totalResults: Int // 13
    )
}