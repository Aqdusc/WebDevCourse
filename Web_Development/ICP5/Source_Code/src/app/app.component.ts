// this is our main typescript file where we create the functionality of the webapp and also create the
//objects and variables to store and display the data and the operations to be performed on the input data
// and the to-do list

//importing all the documents from libraries and angular modules
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Guid } from 'guid-typescript';// importing the guid-typescript after installing it
import { from } from 'rxjs';
import { Todo } from 'src/models/todo.model';//importing the to-do class created in the models file
@Component({
  selector: 'app-root', //here the selector points to this template app.component.ts file
  templateUrl: './app.component.html',// points to the .html file where the html file is written
  styleUrls: ['./app.component.css']// points to the .css file created 
})
//exporting the class Appcomponet
//here we start creating and initializing all the variables to be used in the webapp
//create an array in which all the to-do are added
//have initialized with 2 basic tasks.
export class AppComponent {
  todos: Todo[] = [
    new Todo(Guid.create(),'Wash Car',false),
    new Todo(Guid.create(), 'Buy Groceries', false),

  ]
//here when the form is submitted a new to-do is added to the list
  onSubmit(form: NgForm){
    let todo=new Todo(Guid.create(),form.value.title, false);
    this.todos.push(todo);
    form.resetForm();// we reset the form for new input task to add
  }
// here the oncomplete function moves the completed task to the completed list
  onComplete(id: Guid){
    let todo=this.todos.filter(x=>x.id===id)[0];
    todo.complete=true;

  }
// here on detleting the completed task the function deletes the task form the list by using the id
  onDelete(id: Guid){
    let todo=this.todos.filter(x=>x.id===id)[0];
    let index=this.todos.indexOf(todo,0);
    if(index>-1){
      this.todos.splice(index,1);// finally we perform splice operation
    }
  }
}

