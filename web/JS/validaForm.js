
function validaFormCadastroPessoa()

{

    for (i = 0; i < 12; i++)
    {
        campo = document.formTeste.elements[i];

        if (!campo.value)
        {
            alert('Você não informou o campo ' + campo.name + '!');
            campo.focus()
            return false;
        }
    }

    return true;
}


function validaFormCidade()
{

    for (i = 0; i < 3; i++)
    {
        campo = document.formTeste.elements[i];

        if (!campo.value)
        {
            alert('Você não informou o campo ' + campo.name + '!');
            campo.focus()
            return false;
        }
    }

    return true;
}
function validaFormBairro()
{

    for (i = 0; i < 3; i++)
    {
        campo = document.formTeste.elements[i];

        if (!campo.value)
        {
            alert('Você não informou o campo ' + campo.name + '!');
            campo.focus()
            return false;
        }
    }

    return true;
}
function validaFormEstado()
{

    for (i = 0; i < 3; i++)
    {
        campo = document.formTeste.elements[i];

        if (!campo.value)
        {
            alert('Você não informou o campo ' + campo.name + '!');
            campo.focus()
            return false;
        }
    }

    return true;
}

function validaFormEndereco()
{

    for (i = 0; i < 3; i++)
    {
        campo = document.formTeste.elements[i];

        if (!campo.value)
        {
            alert('Você não informou o campo ' + campo.name + '!');
            campo.focus()
            return false;
        }
    }

    return true;
}
function validaFormRoteador()
{

    for (i = 0; i < 3; i++)
    {
        campo = document.formTeste.elements[i];

        if (!campo.value)
        {
            alert('Você não informou o campo ' + campo.name + '!');
            campo.focus()
            return false;
        }
    }

    return true;
}