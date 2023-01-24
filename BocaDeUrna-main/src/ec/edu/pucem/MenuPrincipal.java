package ec.edu.pucem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ec.edu.pucem.dominios.Prefecto;
import ec.edu.pucem.formularios.BocaDeUrna;
import ec.edu.pucem.formularios.CrearPrefecto;

import javax.swing.JMenuBar;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.CardLayout;

public class MenuPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contenedor;
	private JDesktopPane desktopPane;
	private JMenuItem mntmSalir;
	private JMenuItem mntmCrearPrefectos;
	private JMenuItem mntmBocaDeUrna;
	
	public List<Prefecto> prefectos=new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	// Byron Santiago Serrano Zambrano
	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setTitle("SISTEMA CONTEO DE VOTOS BOCA DE URNA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(192, 191, 188));
		setJMenuBar(menuBar);

		JMenu mnSistema = new JMenu("Sistema");
		mnSistema.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnSistema);

		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(this);
		mntmSalir.setFont(new Font("Dialog", Font.BOLD, 16));
		mnSistema.add(mntmSalir);

		JMenu mnAdministracin = new JMenu("Administración");
		mnAdministracin.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnAdministracin);

		mntmCrearPrefectos = new JMenuItem("Crear Prefectos");
		mntmCrearPrefectos.setFont(new Font("Dialog", Font.BOLD, 16));
		mntmCrearPrefectos.addActionListener(this);
		mnAdministracin.add(mntmCrearPrefectos);

		JMenu mnProceso = new JMenu("Proceso");
		mnProceso.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnProceso);

		mntmBocaDeUrna = new JMenuItem("Boca de Urna");
		mntmBocaDeUrna.setFont(new Font("Dialog", Font.BOLD, 16));
		mntmBocaDeUrna.addActionListener(this);
		mnProceso.add(mntmBocaDeUrna);
		contenedor = new JPanel();
		contenedor.setBackground(new Color(255, 255, 255));
		contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contenedor);
		contenedor.setLayout(new CardLayout(0, 0));

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(193, 193, 255));
		contenedor.add(desktopPane, "name_35522358088801");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmSalir) {
			dispose();
		} else if (e.getSource() == mntmCrearPrefectos) {
			CrearPrefecto crearPrefecto=new CrearPrefecto(prefectos);
			if (crearPrefecto.isVisible() == false) {
				desktopPane.add(crearPrefecto);
				crearPrefecto.setVisible(true);
				Dimension desktopSize = desktopPane.getSize();
		        Dimension FrameSize = crearPrefecto.getSize();
		        crearPrefecto.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
		        crearPrefecto.show();
				}
			

		} else if (e.getSource() == mntmBocaDeUrna) {
			BocaDeUrna bocaUrna=new BocaDeUrna(prefectos);
			if (bocaUrna.isVisible() == false) {
				desktopPane.add(bocaUrna);
				bocaUrna.setVisible(true);
				Dimension desktopSize = desktopPane.getSize();
		        Dimension FrameSize = bocaUrna.getSize();
		        bocaUrna.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
		        bocaUrna.show();
				}
		}
	}
}
