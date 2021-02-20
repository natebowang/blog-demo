package ml.zouren.blogdemo.entity

import javax.persistence.*

@Entity
class Blog(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        @Column(length=2000)
        var title: String,
        @Column(length=5000)
        var content: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Blog
        if (id != other.id) return false
        if (title != other.title) return false
        if (content != other.content) return false

        return true
    }

    override fun hashCode(): Int {
        return id ?: -1
    }
}

