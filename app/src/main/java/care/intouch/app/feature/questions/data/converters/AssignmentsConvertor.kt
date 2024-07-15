package care.intouch.app.feature.questions.data.converters

import care.intouch.app.feature.questions.data.models.AssignmentsBlockDto
import care.intouch.app.feature.questions.data.models.AssignmentsDto
import care.intouch.app.feature.questions.domain.models.Assignments
import care.intouch.app.feature.questions.domain.models.AssignmentsBlock

class AssignmentsConvertor {

    fun map(assignments: Assignments): AssignmentsDto {
        return AssignmentsDto(
            id = assignments.id,
            title = assignments.title,
            text = assignments.text,
            updateDate = assignments.updateDate,
            addDate = assignments.addDate,
            assignmentType = assignments.assignmentType,
            status = assignments.status,
            tags = assignments.tags,
            language = assignments.language,
            share = assignments.share,
            imageUrl = assignments.imageUrl,
            blocks = assignments.blocks.map{
                blockMap(it)
            },
        author = assignments.author,
        authorName = assignments.authorName,
        isPublic = assignments.isPublic,
        isFavorite = assignments.isFavorite,
        averageGrade = assignments.averageGrade,
        )
    }

    private fun blockMap(assignmentsBloc: AssignmentsBlock): AssignmentsBlockDto{

    }
}