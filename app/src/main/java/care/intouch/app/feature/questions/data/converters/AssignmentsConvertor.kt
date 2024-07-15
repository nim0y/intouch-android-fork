package care.intouch.app.feature.questions.data.converters

import care.intouch.app.feature.questions.data.models.AssignmentsBlockDto
import care.intouch.app.feature.questions.data.models.AssignmentsChoiceRepliesDto
import care.intouch.app.feature.questions.data.models.AssignmentsDto
import care.intouch.app.feature.questions.domain.models.Assignments
import care.intouch.app.feature.questions.domain.models.AssignmentsBlock
import care.intouch.app.feature.questions.domain.models.AssignmentsChoiceReplies
import care.intouch.app.feature.questions.domain.models.BlockDescription
import care.intouch.app.feature.questions.domain.models.TypeOfTitle
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
            //description = assignmentsBlock.description,
            description = "Нужно ли это поле вообще???",
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
            description = descriptionParse(assignmentsBlock.description),
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

    private fun descriptionParse(str: String): List<BlockDescription>{
        val result: MutableList<BlockDescription> = mutableListOf()
        var description = str
        val firstKey = "\\\"type\\\":\\\""
        val secondKey = "\\\",\\\"text\\\":\\\""
        val thirdKey = "\\\",\\\"characterList"
        while(description.contains(firstKey)) {
            val firstKeyPosition = description.indexOf(firstKey)
            val secondKeyPosition = description.indexOf(secondKey)
            val thirdKeyPosition = description.indexOf(thirdKey)
            val type = description.substring(firstKeyPosition + firstKey.length, secondKeyPosition)
            val text = description.substring(secondKeyPosition + secondKey.length, thirdKeyPosition)
            result.add(BlockDescription(getTypeOfTitle(type), text))
            description = description.substring(thirdKeyPosition + thirdKey.length)
        }
        return result
    }

    private fun getTypeOfTitle(str: String): TypeOfTitle{
        return when(str){
            "unstyled" -> TypeOfTitle.UNSTYLED
            "header-one" -> TypeOfTitle.HEADER_ONE
            "header-two" -> TypeOfTitle.HEADER_TWO
            else -> TypeOfTitle.UNSTYLED
        }
    }
}