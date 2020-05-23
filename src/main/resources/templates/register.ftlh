<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=["scripts/registration.js"]>

    <div class="mx-auto mt-3 w-50">
        <form id="registration-form">

            <h3>Register</h3>
            <input type="email" name="email" class="form-control mb-1" value="${(dto.email)!''}" placeholder="Email">
            <input type="text" name="name"  pattern=".{4,24}" class="form-control mb-1" value="${(dto.name)!''}" placeholder="Username">
            <input type="password" name="password" class="form-control mb-1" placeholder="Password">
            <button class="btn btn-outline-primary btn-block">Register</button>

            <div>
                <#if errors?? >
                    <p>Error in your input data</p>
                    <#list errors as fieldError>
                        <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                    </#list>
                </#if>
            </div>
        </form>
    </div>
</@main.renderWith>