package care.intouch.app.feature.questions.data.converters

import care.intouch.app.feature.questions.data.models.AssignmentsBlockDto
import care.intouch.app.feature.questions.data.models.AssignmentsChoiceRepliesDto
import care.intouch.app.feature.questions.data.models.AssignmentsDto
import care.intouch.app.feature.questions.domain.models.Assignments
import care.intouch.app.feature.questions.domain.models.AssignmentsBlock
import care.intouch.app.feature.questions.domain.models.AssignmentsChoiceReplies
import javax.inject.Inject

class AssignmentsConvertor @Inject constructor() {

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
            blocks = assignments.blocks.map {
                blockMap(it)
            },
            author = assignments.author,
            authorName = assignments.authorName,
            isPublic = assignments.isPublic,
            isFavorite = assignments.isFavorite,
            averageGrade = assignments.averageGrade,
        )
    }

    private fun blockMap(assignmentsBlock: AssignmentsBlock): AssignmentsBlockDto {
        return AssignmentsBlockDto(
            id = assignmentsBlock.id,
            choiceReplies = assignmentsBlock.choiceReplies?.map {
                choiceRepliesMap(it)
            },
            leftPole = assignmentsBlock.leftPole,
            rightPole = assignmentsBlock.rightPole,
            image = assignmentsBlock.image,
            question = assignmentsBlock.question,
            reply = assignmentsBlock.reply,
            description = assignmentsBlock.description,
            type = assignmentsBlock.type,
            startRange = assignmentsBlock.startRange,
            endRange = assignmentsBlock.endRange
        )
    }

    private fun choiceRepliesMap(choiceReplies: AssignmentsChoiceReplies): AssignmentsChoiceRepliesDto {
        return AssignmentsChoiceRepliesDto(
            id = choiceReplies.id,
            reply = choiceReplies.reply,
            checked = choiceReplies.checked
        )
    }

    fun map(assignments: AssignmentsDto): Assignments {
        return Assignments(
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
            blocks = assignments.blocks.map {
                blockMap(it)
            },
            author = assignments.author,
            authorName = assignments.authorName,
            isPublic = assignments.isPublic,
            isFavorite = assignments.isFavorite,
            averageGrade = assignments.averageGrade,
        )
    }

    private fun blockMap(assignmentsBlock: AssignmentsBlockDto): AssignmentsBlock {
        return AssignmentsBlock(
            id = assignmentsBlock.id,
            choiceReplies = assignmentsBlock.choiceReplies?.map {
                choiceRepliesMap(it)
            },
            leftPole = assignmentsBlock.leftPole,
            rightPole = assignmentsBlock.rightPole,
            image = assignmentsBlock.image,
            question = assignmentsBlock.question,
            reply = assignmentsBlock.reply,
            description = assignmentsBlock.description,
            type = assignmentsBlock.type,
            startRange = assignmentsBlock.startRange,
            endRange = assignmentsBlock.endRange
        )
    }

    private fun choiceRepliesMap(choiceReplies: AssignmentsChoiceRepliesDto): AssignmentsChoiceReplies {
        return AssignmentsChoiceReplies(
            id = choiceReplies.id,
            reply = choiceReplies.reply,
            checked = choiceReplies.checked
        )
    }
}