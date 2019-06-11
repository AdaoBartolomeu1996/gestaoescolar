$(function() {

    $.validator.addMethod("phone", function(value, element) {
        return this.optional(element) || value == value.match(/^[9]{1}[1-9]{1}[1-9]{1}[0-9]{6}/);

    },"Numero Inválido");

    $.validator.addMethod("numeroBilhete", function(value, element) {
        return this.optional(element) || value == value.match(/^[0-9]{9}[a-zA-Z]{2}[0-9]{3}/);

    },"Bilhete Inválido");

    $.validator.addMethod("alpha", function(value, element) {
        return this.optional(element) || value == value.match(/^[a-zA-Z\s]+$/);
    },"Por favor digite apenas letra");

    $('#formulario').validate( {

        debug:false,

       rules:{

           telefone:{
               required: true,
               number:true,
               phone: true
           },

           bilhete:{
               required: true,
               numeroBilhete:true
           },

           estadoSexo:{
               required: true
           },

           data:{
               required: true
           },

            email:{
                required: true,
                email: true
            },

           //curso:{
          //     required: true
        //   },

           departamento:{
               required: true
           },

          // descricao:{
          //     required: true,
          //     rangelength:[50, 200]
         //  },
           nome:{
               required: true,
               minlength: 5,
               alpha:true
           }
       },
        messages:{

            telefone:{
                required: "Por favor digite o número do telefone.",
                number:"Por favor digite apenas número.",

            },

            bilhete:{
                required: "Por favor digite o número do bilhete.",

            },

            estadoSexo:{
                required: "Por favor selecione o sexo."
            },

            data:{
                required: "Por favor selecione uma data."
            },
            email:{
                required: "Por favor digite o email.",
                email: "Porvafor digite email valido"
            },

            //curso:{
           //     required:"Por favor selecione um curso."
          //  },

            departamento:{
                required:"Por favor selecione um departamento."
            },

          /*  descricao:{
                required: "Por favor degite a descrição.",
                rangelength:"A descrição deve conter 50 a 200 cractéres."
            },*/
            nome:{
                required: "Por favor degite o nome.",
                minlength:"O nome deve conter mais de 5 caractéres."
            }
        }
    });
});