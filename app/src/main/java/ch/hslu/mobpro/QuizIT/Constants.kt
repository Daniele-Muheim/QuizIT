package ch.hslu.mobpro.QuizIT


class Constants {

    companion object {

        const val USER_NAME = "user_name"
        const val TOTAL_QUESTIONS = "total_question"
        const val CORRECT_ANSWERS = "correct_answers"

        fun getQuestions(): ArrayList<Question> {
            val questionList = ArrayList<Question>()

            val firstQuestion = Question(1, "Welche Layout Pixel-Angabe ist in Kotlin nicht empfohlen?",
            "Pixel (px)", "Density-independent Pixels (dp)", "Scale-independent Pixels (sp) ","" ,1)
            questionList.add(firstQuestion)

            val secondQuestion = Question(1, "Test",
            "Test", "Test", "Test", "Test", 2)
            questionList.add(secondQuestion)

            val thirdQuestion = Question(1, "Test",
            "Test", "Test", "Test", "Test", 1)
            questionList.add(thirdQuestion)

            val fourthQuestion = Question(1, "Test",
            "Test", "Test", "Test", "Test", 4)
            questionList.add(fourthQuestion)

            val fifthQuestion = Question(1, "Test",
            "Test", "Test", "Test", "Test", 1)
            questionList.add(fifthQuestion)

            val sixthQuestion = Question(1, "Test",
            "Test", "Test", "Test", "Test", 1)
            questionList.add(sixthQuestion)

            val seventhQuestion = Question(1, "Test",
            "Test", "Test", "Test", "Test", 2)
            questionList.add(seventhQuestion)

            val eighthQuestion = Question(1, "Test",
            "Test", "Test", "Test", "Test", 3)
            questionList.add(eighthQuestion)
            
            val ninthQuestion = Question(1, "Test",
            "Test", "Test", "Test", "Test", 4)
            questionList.add(ninthQuestion)

            val tenthQuestion = Question(1, "Test",
            "Test", "Test", "Test", "Test", 4)
            questionList.add(tenthQuestion)

            return questionList
        }
    }

}
