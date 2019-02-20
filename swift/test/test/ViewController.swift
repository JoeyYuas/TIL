//
//  ViewController.swift
//  test
//
//  Created by 湯浅成生 on 2019/02/20.
//  Copyright © 2019 test_name. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var label: UILabel!
    @IBOutlet weak var button: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    
    @objc func buttonOnTapped(sender: UIButton) {
        let now = Date() // 現在時刻を取得
        let dateFormatter = DateFormatter()
        dateFormatter.locale = Locale(identifier: "ja_JP") // ロケールを指定
        dateFormatter.dateFormat = "yyyy/MM/dd HH:mm:ss" // 時刻表示のフォーマットを指定
        // 指定したフォーマットで現在時刻を取得してラベルに反映させる
        label.text = dateFormatter.string(from: now)
        func viewDidLoad() {
            super.viewDidLoad()
            button.addTarget(self, action: #selector(ViewController.buttonOnTapped(sender:)), for: .touchUpInside)
        }
    }
}
