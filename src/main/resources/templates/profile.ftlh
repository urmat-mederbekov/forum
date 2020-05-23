<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <#if dto??>
        <h1>Your data</h1>
        <p>${(dto.username)!'-- missing --'} - ${(dto.email)!'-- email missing --'}</p>
    <#else>
        <h1>Пользователь не найден!</h1>
    </#if>
</@main.renderWith>