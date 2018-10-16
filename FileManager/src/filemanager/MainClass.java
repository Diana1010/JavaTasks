package filemanager;



import java.util.Scanner;

import javax.swing.filechooser.FileSystemView;
	public class MainClass {
		 public static void main(String[] args) {
		        
		        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
		        FileManager fm = new FileManager();
		        fm.currentFolder = fileSystemView.getDefaultDirectory();
		        
		       
		        while(true){
		            
		            try{
		                
		                Scanner scan = new Scanner(System.in);
		                System.out.println(fm.currentFolder+":" );               
		                String input = scan.nextLine();
		                String[] cmd = input.split(" ");
		                
		                switch(cmd[0]){
		                    
		                    case "cd":
		                        if(cmd.length == 2){
		                        
		                            fm.changeFolder(cmd[1]);
		                        }else{
		                            System.out.println("Неправильные аргументы");
		                        }
		                        break;
		                        
		                    case "list":
		                        if(cmd.length == 1){
		                            fm.list(fm.currentFolder);
		                        }
		                        
		                        else{
		                            System.out.println("Неправильные аргументы");
		                        }
		                        break;
		                        
		                    case "newdir":
		                        if (cmd.length > 1){
		                           {
		                                for(int i = 1; i < cmd.length; i++){
		                                    fm.createDir(cmd[i], cmd[1]);
		                                }
		                            }
		                            
		                        }else{
		                            System.out.println("Неправильные аргументы");
		                        }
		                        break;
		                        
		                    case "newfile":
		                        if (cmd.length > 1){
		                                for(int i = 1; i < cmd.length; i++){
		                                    fm.createFile(cmd[i], cmd[1]);
		                                }   
		                        }
		                        else {
		                            System.out.println("Неправильные аргументы");
		                        }
		                        break;
		                        
		                    case "move":
		                        
		                        int length = cmd.length;
		                        
		                        for(int i = 1; i < length-1; i++){
		                            fm.move(cmd[i], cmd[length-1]);
		                        }
		                        
		                        break;
		                        
		                    case"quit":
		                    	System.out.println("Программа завершила свою работу");
		                    	scan.close();
		                    	System.exit(0);
		                        break ;
		                    
		                    default:
		                        System.out.println("Неизвестная команда");
		                }
		                
		                
		            }catch(Exception e){
		                System.out.println(e);
		            }
		       
		        
		        }
		 }
}
