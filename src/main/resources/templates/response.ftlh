<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=["/scripts/response.js"]>

    <div class="subject my-3">
        <div class="row">
            <div class="id" style="display: none;">${dto.id}</div>
            <div class="col">${dto.name}</div>
            <div class="col">${dto.dateTime}</div>
            <div class="col">${dto.owner.username}</div>
        </div>
        <hr>
        <div class="row">
            <div class="col">${dto.content}</div>
        </div>
    </div>

    <div class="responses">

    </div>
    <form class="responseForm" action="/comment" method="post">
        <input type="hidden" name="id" value="${dto.id}">
        <textarea name="text"></textarea>
        <button type="submit" class="send">Send</button>
    </form>

    <#include "partials/pagination.ftlh">
</@main.renderWith>