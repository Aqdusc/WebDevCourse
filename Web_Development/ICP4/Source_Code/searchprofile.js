$(function () {//creating the jquery function which will activate when the search button is clicked.
    $("#Search").click(function (e) {

        var result;
        e.preventDefault(); //to load the page and prevent refreshing
        $.ajax({ //Call the github api
            type: 'GET', //Read-Only data so only get and no post
            url: 'https://api.github.com/users/' + $('#Username').val(), //get the github user through url
            data: 'json', // data will be returned in java script object nota
            success: function (data) { //all the data being stored in the data object
                console.log('success', data); // if successful log to the console success
                $("#gituser").html("GitHub Profile Name is : "+data.login); // Getting the github user name
                $("#gitid").html("GitHub ID is: "+data.id); // Getting the github userID
                $("#link").html(data.html_url); // Getting the github URL
                $("#link").attr("href", data.html_url); //Adding the URL as href
                $("#repo").html("No. of Repositories: "+data.public_repos); //Adding the no of repositories of the user
                $("#pimg").attr("src", data.avatar_url); // getting the user profile github image
            },
            error: function (data) {
                console.log('error' + $('#Username').val()); // error display if error occurs, like what type of error
            }
        });

        return result; //returning the result to the html file.
    });
});