package care.intouch.app.feature.questions.presentations.ui.questions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.navigation.TopBarArcButton
import care.intouch.uikit.ui.questions.TextFieldWithCheckbox
import care.intouch.uikit.ui.questions.TextFieldWithCheckmars
import care.intouch.uikit.ui.questions.TextFieldWithSliderAndDigits
import care.intouch.uikit.ui.textFields.MultilineTextField

@Composable
private fun QuestionsScreen() {
    Box(
        modifier = Modifier
            .background(InTouchTheme.colors.mainBlue)
            .verticalScroll(rememberScrollState())
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 27.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            TopBarArcButton(
                onClick = { /*TODO*/ },
                enabled = true,
                modifier = Modifier.align(Alignment.End)
            )
            Spacer(modifier = Modifier.height(48.dp))
            MultilineTextField(
                subtitleText = StringVO.Resource(R.string.motivates_question),
                captionText = StringVO.Resource(R.string.inscribe_motivates_question),
                value = "",
                modifier = Modifier.fillMaxWidth(),
                hint = StringVO.Resource(R.string.write_your_answer_here),
                onValueChange = { },
                isError = false,
                enabled = true)
            Spacer(modifier = Modifier.height(40.dp))
            TextFieldWithCheckbox(
                modifier = Modifier.fillMaxWidth(),
                subtitleText = StringVO.Resource(R.string.skills_question),
                checkboxText = StringVO.Resource(R.string.skills_leadership_answer),
                secondCheckboxText = StringVO.Resource(R.string.skills_communication_answer),
                thirdCheckboxText = StringVO.Resource(R.string.skills_problem_solving_answer),
                fourthCheckboxText = StringVO.Resource(R.string.skills_technical_expertise_answer)
            )
            Spacer(modifier = Modifier.height(40.dp))
            TextFieldWithCheckmars(
                modifier = Modifier.fillMaxWidth(),
                subtitleText = StringVO.Resource(R.string.professional_development_question),
                captionText = StringVO.Resource(R.string.inscribe_professional_development_question),
                checkmarkText = StringVO.Resource(R.string.professional_development_attending_workshops_or_conferences_answer),
                secondCheckmarkText = StringVO.Resource(R.string.professional_development_pursuing_further_education_or_certifications),
                thirdCheckmarkText = StringVO.Resource(R.string.professional_development_participating_in_mentorship_programs),
                fourthCheckmarkText = StringVO.Resource(R.string.professional_development_joining_industry_related_associations)
            )
            Spacer(modifier = Modifier.height(40.dp))
            TextFieldWithSliderAndDigits(
                subtitleText = StringVO.Resource(R.string.scale_questions),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 1920)
@Composable
fun QuestionsScreenPreview() {
    InTouchTheme {
        QuestionsScreen()
    }
}