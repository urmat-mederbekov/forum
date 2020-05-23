<#import "partials/main.ftlh" as main />

<@main.renderWith>

    <div class="subjects">
        <#if items??>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Date</th>
                    <th scope="col">Author</th>
                    <th scope="col">Responses</th>
                </tr>
                </thead>
                <#list items as item>
                    <tbody>
                    <tr id="${item.id}">

                        <td><a href="/subjects/${item.id}/responses">${item.name}</a></td>
                        <td><a href="/subjects/${item.id}/responses">${item.dateTime}</a></td>
                        <td><a href="/subjects/${item.id}/responses">${item.owner.username}</a></td>
                        <td><a href="/subjects/${item.id}/responses">${item.msgNumber}</a></td>


                    </tr>

                    </tbody>
                </#list>
            </table>
        <#else>
            <p>There is currently no subject. Create a new one</p>
        </#if>
    </div>

    <form class="subjectForm" action="/api/subjects" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <input type="text" name="name">
        <textarea name="content"></textarea>
    <button type="submit" class="send">Send</button>
    </form>

    <#include "partials/pagination.ftlh">
</@main.renderWith>