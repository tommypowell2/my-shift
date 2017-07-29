import {Company} from "./company";
/**
 * Created by tpowell on 12/17/16.
 */

export class Administrator{
    company = new Company;
    password;

    constructor(public firstName:string , public lastName:string, public userName:string){

    }
}