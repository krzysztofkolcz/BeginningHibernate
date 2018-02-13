jQuery(document).ready(function(){
    console.log("ready");
    jQuery(".answerInputBtn").click(function(){
        console.log(this.id);
        questionId = jQuery(this).data("id");
        console.log(questionId);
        console.log(jQuery("#answerInput"+questionId).val());
        jQuery.post("/answers/add",function(data){

        });

    })
});