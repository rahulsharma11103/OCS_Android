
// AgoraTutorial.h : PROJECT_NAME
//

#pragma once

#ifndef __AFXWIN_H__
	#error "�ڰ������ļ�֮ǰ������stdafx.h�������� PCH �ļ�"
#endif

#include "resource.h"


// CAgoraTutorialApp: 

class CAgoraTutorialApp : public CWinApp
{
public:
	CAgoraTutorialApp();

public:
	virtual BOOL InitInstance();

	DECLARE_MESSAGE_MAP()
};

extern CAgoraTutorialApp theApp;