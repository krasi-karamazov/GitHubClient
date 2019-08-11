package kpk.dev.model.query

class RepoDetailsQuery(owner: String, repoName: String): Query() {
    val query = "query{\n" +
            "  repository(owner:\"$owner\", name:\"$repoName\") {\n" +
            "    openissues: issues(states:CLOSED) {\n" +
            "\t\t\ttotalCount\n" +
            "    }\n" +
            "    closedissues: issues(states:OPEN) {\n" +
            "\t\t\ttotalCount\n" +
            "    }\n" +
            "    \n" +
            "    pullRequests{\n" +
            "      totalCount\n" +
            "    }\n" +
            "  }\n" +
            "}\n"
}