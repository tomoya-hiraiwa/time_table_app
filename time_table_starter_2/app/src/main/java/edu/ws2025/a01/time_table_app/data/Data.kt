package edu.ws2025.a01.time_table_app.data

import edu.ws2025.a01.time_table_app.ui.theme.bijutuColor
import edu.ws2025.a01.time_table_app.ui.theme.eigoColor
import edu.ws2025.a01.time_table_app.ui.theme.gijutuColor
import edu.ws2025.a01.time_table_app.ui.theme.kateikaColor
import edu.ws2025.a01.time_table_app.ui.theme.kokugoColor
import edu.ws2025.a01.time_table_app.ui.theme.ongakuColor
import edu.ws2025.a01.time_table_app.ui.theme.otherColor
import edu.ws2025.a01.time_table_app.ui.theme.rikaColor
import edu.ws2025.a01.time_table_app.ui.theme.sougouColor
import edu.ws2025.a01.time_table_app.ui.theme.sugakuColor
import edu.ws2025.a01.time_table_app.ui.theme.syakaiColor
import edu.ws2025.a01.time_table_app.ui.theme.taiikuColor

//教科の一覧リスト
//TODO 3.教科名を変更したり追加したりしてみよう
val subjectList = listOf<SubjectData>(
    SubjectData(
        "国語",
        kokugoColor
    ),
    SubjectData(
        "数学",
        sugakuColor
    ),
    SubjectData(
        "理科",
        rikaColor
    ),
    SubjectData(
        "社会",
        syakaiColor
    ),
    SubjectData(
        "英語",
        eigoColor
    ),
    SubjectData(
        "音楽",
        ongakuColor
    ),
    SubjectData(
        "美術",
        bijutuColor
    ),
    SubjectData(
        "体育",
        taiikuColor
    ),
    SubjectData(
        "技術",
        gijutuColor
    ),
    SubjectData(
        "家庭",
        kateikaColor
    ),
    SubjectData(
        "総合",
        sougouColor
    ),
    SubjectData(
        "その他",
        otherColor
    ),
    SubjectData(
        "平野",
        otherColor
    ),
    )


