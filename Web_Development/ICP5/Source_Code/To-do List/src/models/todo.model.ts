//here in todo.model.ts i have imported Guid for marking each task input to an id when inputted
import { Guid } from "guid-typescript";
//creating the class todo with the variables in the constructor
export class Todo{
    constructor(
        public id:Guid,// for each task an id is assigned
        public title: string, //for each task the input is string
        public complete:boolean //for completion we create a boolean to check if task is completed.
    ){}
}
