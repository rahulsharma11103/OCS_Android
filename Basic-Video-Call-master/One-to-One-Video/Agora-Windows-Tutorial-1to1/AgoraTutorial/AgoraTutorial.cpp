//

#include "stdafx.h"
#include "AgoraTutorial.h"
#include "AgoraTutorialDlg.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CAgoraTutorialApp

BEGIN_MESSAGE_MAP(CAgoraTutorialApp, CWinApp)
	ON_COMMAND(ID_HELP, &CWinApp::OnHelp)
END_MESSAGE_MAP()


CAgoraTutorialApp::CAgoraTutorialApp()
{
	m_dwRestartManagerSupportFlags = AFX_RESTART_MANAGER_SUPPORT_RESTART;
}

CAgoraTutorialApp theApp;


BOOL CAgoraTutorialApp::InitInstance()
{
	INITCOMMONCONTROLSEX InitCtrls;
	InitCtrls.dwSize = sizeof(InitCtrls);
	InitCtrls.dwICC = ICC_WIN95_CLASSES;
	InitCommonControlsEx(&InitCtrls);

	CWinApp::InitInstance();


	AfxEnableControlContainer();

	SetRegistryKey(_T("Ӧ�ó��������ɵı���Ӧ�ó���"));

//	CMFCVisualManagerOffice2007::SetStyle(CMFCVisualManagerOffice2007::Office2007_LunaBlue);
//	CMFCVisualManager::SetDefaultManager(RUNTIME_CLASS(CMFCVisualManagerOffice2007));


	CAgoraTutorialDlg dlg;
	m_pMainWnd = &dlg;
	INT_PTR nResponse = dlg.DoModal();
	if (nResponse == IDOK)
	{

	}
	else if (nResponse == IDCANCEL)
	{

	}
	else if (nResponse == -1)
	{
		TRACE(traceAppMsg, 0, "����: �Ի��򴴽�ʧ�ܣ�Ӧ�ó���������ֹ��\n");
	}

	return FALSE;
}

