$(function() {

    $('#formulario').validate( {

       rules:{
           descricao:{
               required: true,
               minlength: 50
           },

           nome:{
               required: true,
               lettersonly: true
           }

       },
        messages:{
           nome:{
               required: 'Por favor degite o nome.',
               lettersonly:'Por favor só letras.'
           },
            descricao:{
                required: 'Por favor degite a descrição.',
                minlength:'A descrição deve conter mais de 50 caractéres.'
            }
        }
    });

});