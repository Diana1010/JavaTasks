package filemanager;

import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.Arrays;

public class FileManager {
	
	private static final int sizeLength = 20;
    private static final int dateLength = 30;
    
	 public File currentFolder;
	  
	    public boolean changeFolder(String toPath){	    	
	        File toFolder = new File(toPath);      
	        if(toFolder.exists() && toFolder.isDirectory()){	            
	            currentFolder = toFolder;
	            return true;
	        }
	        
	 
	           
	        if(!toFolder.exists()){
	            
	            File[] files = currentFolder.listFiles();
	            for(File file: files){
	                
	                if(file.isDirectory() && toPath.equals(file.getName())){
	                    currentFolder = file;
	                    return true;
	                }
	            }
	            System.out.println("Каталог не существует");	           
	        }
	        return false;
	    }
	    
	    
	    
	    
	    
	    public void list(File f){
	        final String sizeName ="size";
	        final String modifName = "last modified";
	        final String pathName = "path";
	        
	        if (f.exists() && f.isDirectory()){
	            
	            StringBuilder sb = new StringBuilder();
	            sb.append(sizeName).append(new String(new char[sizeLength - sizeName.length()]).replace("\0", " ")).append(" | ");
	            sb.append(modifName).append(new String(new char[dateLength - modifName.length()]).replace("\0", " ")).append(" | ");        
	            sb.append(pathName).append("\n") ;                    
	            File[] files = f.listFiles();
	            
	            for(File file: files){
	                String modifiedDate = new Date(file.lastModified()).toString();
	               
	                sb.append(file.length()).append(new String(new char[sizeLength - String.valueOf(file.length()).length()]).replace("\0", " ")).append(" | ");
	                sb.append(modifiedDate).append(new String(new char[dateLength - modifiedDate.length()]).replace("\0", " ")).append(" | ");
	                sb.append(file.getPath()).append("\n");  
	            }
	            System.out.println(sb.toString());
	        }else{
	            System.out.println("Каталог или файл не существует");
	        }        
	    }
	    
	    
	    
	    
	    public void createDir(String dirName, String key){
	            
	            File newDir = new File(currentFolder.getAbsolutePath()+"/"+dirName);          
	            
	            if(!newDir.exists()){
	                newDir.mkdir();
	                System.out.println("Каталог "+newDir.getName()+" успешно создан!");
	            }else{
	                System.out.println("Каталог уже существуетs");
	            }
        
	    }
	    
	    public void createFile(String fileName, String key){ 
	            File newFile = new File(currentFolder.getAbsolutePath()+"/"+fileName);          	            
	            if(!newFile.exists()){
	                try {
	                    newFile.createNewFile();
	                } catch (IOException ex) {
	                   ex.getMessage();
	                }
	                System.out.println("Файл "+newFile.getName()+" успешно создан");
	            }else{
	                System.out.println("Файл уже существует");
	            }  	        
	    }
	    
	    
	    public void move(String sourcePath, String destPath){
	        Path from, to;
	        if(pathContains(sourcePath)){
	            from = Paths.get(currentFolder + "/" + sourcePath);
	       }
	       else{
	            from = Paths.get(sourcePath);
	        }
	           System.out.println(from.toString()); 
	        if(pathContains(destPath)){
		            to = Paths.get(currentFolder + "/" + destPath);
		       }
		    else{
		            to = Paths.get(destPath);
		        }
	      
	        System.out.println(to.toString());    
	        try{
	            Files.move(from, to);
	            System.out.println("Файл " + from.getFileName() + " успешно переместили " + to);
	        }catch(IOException e){
	            System.out.println("Исходный путь или путь назначения не существует");
	        }
	        
	        
	    }
	    
	    public boolean pathContains(String toPath){
	        File[] files = currentFolder.listFiles();
	            for(File file: files){
	                
	                if( toPath.equals(file.getName())){
	                    return true;
	                }
	            }
	        return false;
	    }
}
