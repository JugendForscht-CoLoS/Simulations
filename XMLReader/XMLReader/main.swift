//
//  main.swift
//  XMLReader
//
//  Created by Tim Jaeger on 17.03.21.
//

import Foundation
import SWXMLHash

do {
    
    let fileManager = FileManager.default
    
    let sunData = try fileManager.contentsOfDirectory(atPath: "/Users/timjaeger/Desktop/JuFo/Programme/Simulations/SunData")
    
    var fileIndex = 0
    
    for file in sunData {
        
        let content = try String(contentsOfFile: "/Users/timjaeger/Desktop/JuFo/Programme/Simulations/SunData/" + file)
        
        let xml = SWXMLHash.parse(content)
        
        var date = ""
        var location = ""
        var startTimeUTC = ""
        var azimuts: [String] = []
        var elevations: [String] = []
        
        if let content = xml["html"]["body"]["table"]["tr"][0]["td"][1].element?.text {
            
            date = content
        }
        if let content = xml["html"]["body"]["table"]["tr"][2]["td"][1].element?.text {
            
            location = content
        }
        if let content = xml["html"]["body"]["table"]["tr"][5]["td"][0].element?.text {
            
            startTimeUTC = content
        }
        
        var index = 0
        
        for tr in xml["html"]["body"]["table"]["tr"].all {
            
            if index < 5 {
                
                index += 1
                continue
            }
            
            if let content = tr["td"][1].element?.text {
                
                elevations.append(content)
            }
            if let content = tr["td"][2].element?.text {
                
                azimuts.append(content)
            }
        }
        
        var file = ""
        
        file += location + "\n"
        file += date + "\n"
        file += startTimeUTC + "\n"
        
        for i in 0 ..< (elevations.count - 1) {
            
            file += azimuts[i] + ";" + elevations[i] + "\n"
        }
        
        let url = URL(fileURLWithPath: "/Users/timjaeger/Desktop/JuFo/Programme/Simulations/SimulationPrograms/datafiles/sunData/data\(fileIndex).sundata")
        
        try file.write(to: url, atomically: true, encoding: .utf8)
        
        fileIndex += 1
    }
    
}
catch {
    
    print(error.localizedDescription)
}
