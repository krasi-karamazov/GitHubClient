package kpk.dev.model.query

import com.google.gson.annotations.SerializedName

class ReposQuery(pageSize: Int, user: String, cursorToNextPage: String?): Query() {

    @SerializedName("query")
    val query = "query{\n" +
            "    user(login: \"$user\") {\n" +
            "        repositories(first: $pageSize," + getCursorString(cursorToNextPage) + "orderBy: {field:NAME, direction:ASC}) {\n" +
            "            pageInfo {hasNextPage, endCursor}\n" +
            "            nodes {\n" +
            "                id\n" +
            "                name\n" +
            "                url\n" +
            "                isPrivate\n" +
            "                owner {\n" +
            "                    login\n" +
            "                }\n" +
            "                defaultBranchRef {\n" +
            "                    name\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}"

    private fun getCursorString(cursorToNextPage: String?): String {
        return if(cursorToNextPage != null) {
            "after: \"$cursorToNextPage\","
        }else{
            ""
        }
    }
}