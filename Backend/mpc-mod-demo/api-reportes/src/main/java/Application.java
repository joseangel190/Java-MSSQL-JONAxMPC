/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import org.pe.mpc.demo.dto.beans.DtoConnection;
import org.pe.mpc.demo.dto.reports.PathReports;
import jakarta.annotation.PostConstruct;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.boot.system.ApplicationHome;
import java.io.File;

@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = {"org.pe.mpc"})
public class Application {
    @Value("${servidor1.sqlserver.host}")
    String host;
    
    @Value("${servidor1.sqlserver.port}")
    Integer port;
    
    @Value("${servidor1.sqlserver.namedatabase}")
    String nameDatabase;
    
    @Value("${servidor1.sqlserver.userdatabase}")
    String userDatabase;
    
    @Value("${servidor1.sqlserver.passworddatabase}")
    String passwordDatabase;
    
    @PostConstruct
    public void init() {    
        TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
        DtoConnection.host=host;
        DtoConnection.port=port;
        DtoConnection.nameDatabase=nameDatabase;
        DtoConnection.userDatabase=userDatabase;
        DtoConnection.passwordDatabase=passwordDatabase;
    }
    
    @PostConstruct
    public void loadPathReports() {
        ApplicationHome home = new ApplicationHome(Application.class);
        String pathJar=home.getSource().getAbsolutePath();
        String parentDirectory=new File(pathJar).getParent();
        PathReports.DIRECTORYJAR=parentDirectory;
        String pathDirectoryReportDefault=String.format("%s%s%s%s%s%s%s", File.separator,"mpc",File.separator,"reportes",File.separator,"default",File.separator);
        PathReports.DIRECTORYREPORTSDEFAULT=pathDirectoryReportDefault;
    }
        
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
