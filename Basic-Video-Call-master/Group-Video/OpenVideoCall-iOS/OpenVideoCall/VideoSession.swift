//
//  VideoSession.swift
//  OpenVideoCall
//
//  Created by GongYuhua on 3/28/16.
//  Copyright © 2016 Agora. All rights reserved.
//

import UIKit
import AgoraRtcEngineKit

class VideoSession: NSObject {
    
    var uid: UInt
    var hostingView: VideoView
    var canvas: AgoraRtcVideoCanvas
    var size: CGSize?
    
    var mediaInfo = MediaInfo() {
        didSet {
            hostingView.update(with: mediaInfo)
        }
    }
    
    var isVideoMuted = false {
        didSet {
            hostingView.isVideoMuted = isVideoMuted
        }
    }
    
    init(uid: UInt) {
        self.uid = uid
        
        hostingView = VideoView(frame: CGRect(x: 0, y: 0, width: 100, height: 100))
        canvas = AgoraRtcVideoCanvas()
        canvas.uid = uid
        canvas.view = hostingView.videoView
        canvas.renderMode = .hidden
    }
}

extension VideoSession {
    static func localSession() -> VideoSession {
        return VideoSession(uid: 0)
    }
    
    func updateMediaInfo(resolution: CGSize? = nil, fps: Int? = nil) {
        if let resolution = resolution {
            mediaInfo.dimension = resolution
        }
        
        if let fps = fps {
            mediaInfo.fps = fps
        }
        
        hostingView.update(with: mediaInfo)
    }
}
