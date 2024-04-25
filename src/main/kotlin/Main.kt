fun main() {

    val numbersOfComments = NumbersOfComments(3)
    val photo = Photo(1, 1, 1, 1, "Красивое фото", 13)
    val photoAttachment = PhotoAttachment(photo = photo)
    val audio = Audio(1, 1, "Engelbert Humperdinck", "From Here To Eternity", 183)
    val audioAttachment = AudioAttachment(audio = audio)
    val video = Video(1, 1, "My video", "Clip", 183)
    val videoAttachment = VideoAttachment(video = video)
    val file = File(1, 1, "Document", 1024, "pdf")
    val fileAttachment = FileAttachment(file = file)
    val graffiti = Graffiti(1, 1, "https://graffiti.com/music.png", 130, 130)
    val graffitiAttachment = GraffitiAttachment(graffiti = graffiti)


    WallService.add(
        Post(
            1,
            2,
            1,
            3,
            "Hello!",
            "hi",
            3,
            33,
            3,
            2,
            numbersOfComments,
            arrayOf(
                photoAttachment,
                audioAttachment,
                videoAttachment,
                fileAttachment,
                graffitiAttachment
            )
        )
    )

    WallService.printPosts()
    val comment = Comment(1, 1, 2, "Новый комментарий", 123)
    val createdComment = WallService.createComment(1, comment)
    println("$createdComment")

}
