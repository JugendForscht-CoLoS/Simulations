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
        
        var charArray = Array(content)
        
        for i in 0 ..< 65 {
            
            charArray.remove(at: i)
        }
        
        charArray.insert("'", at: 10)
        charArray.insert("'", at: 13)
        
        let validContent = String(charArray)
        
        let xml = SWXMLHash.parse(validContent)
        
        var date = ""
        var location = ""
        var startTimeUTC = ""
        var azimuts: [String] = []
        var elevations: [String] = []
        
        if let content = xml["html"]["body"]["table"]["tr"][0][1].element?.text {
            
            date = content
        }
        if let content = xml["html"]["body"]["table"]["tr"][2][1].element?.text {
            
            location = content
        }
        if let content = xml["html"]["body"]["table"]["tr"][4][0].element?.text {
            
            startTimeUTC = content
        }
        
        var index = 0
        
        for tr in xml["html"]["body"]["table"]["tr"].all {
            
            if index < 4 {
                
                index += 1
                continue
            }
            
            if let content = tr["td"][1].element?.text {
                
                elevations.append(content)
            }
            if let content = tr["td"][1].element?.text {
                
                azimuts.append(content)
            }
        }
        
        var file = ""
        
        file += location + "\n"
        file += date + "\n"
        file += startTimeUTC + "\n"
        
        print(file)
        
        for i in 0 ..< elevations.count {
            
            file += azimuts[i] + ";" + elevations[i] + "\n"
        }
        
        let url = URL(fileURLWithPath: "/Users/timjaeger/Desktop/JuFo/Programme/Simulations/Workspace/data\(fileIndex).sundata")
        
        try file.write(to: url, atomically: true, encoding: .utf8)
        
        fileIndex += 1
    }
    
}
catch {
    
    print(error.localizedDescription)
}
