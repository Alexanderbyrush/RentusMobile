import 'package:flutter/material.dart';

import 'screens/simple_screen.dart';
import 'widgets/app_scaffold.dart';
import 'widgets/section_card.dart';

void main() {
  runApp(const RentusFlutterApp());
}

class RentusFlutterApp extends StatelessWidget {
  const RentusFlutterApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Rentus Flutter',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: const Color(0xFF1B263B)),
        useMaterial3: true,
      ),
      initialRoute: '/login',
      routes: {
        '/login': (_) => const LoginScreen(),
        '/register': (_) => const RegisterScreen(),
        '/home': (_) => const HomeScreen(),
        '/properties': (_) => const PropertiesScreen(),
        '/property-create': (_) => const PropertyCreateScreen(),
        '/property-detail': (_) => const PropertyDetailScreen(),
        '/property-edit': (_) => const PropertyEditScreen(),
        '/about': (_) => const AboutScreen(),
        '/profile': (_) => const ProfileScreen(),
        '/notifications': (_) => const NotificationsScreen(),
        '/contracts': (_) => const ContractsScreen(),
        '/payments': (_) => const PaymentsScreen(),
        '/maintenance': (_) => const MaintenanceScreen(),
        '/my-requests': (_) => const MyRequestsScreen(),
        '/requests': (_) => const RequestsScreen(),
        '/my-reports': (_) => const MyReportsScreen(),
        '/settings': (_) => const SettingsScreen(),
      },
    );
  }
}

class LoginScreen extends StatelessWidget {
  const LoginScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: SingleChildScrollView(
          padding: const EdgeInsets.all(24),
          child: ConstrainedBox(
            constraints: const BoxConstraints(maxWidth: 420),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                const Icon(Icons.home_work_rounded, size: 84, color: Color(0xFF1B263B)),
                const SizedBox(height: 24),
                Text('Iniciar sesión', style: Theme.of(context).textTheme.headlineMedium),
                const SizedBox(height: 16),
                const TextField(decoration: InputDecoration(labelText: 'Correo', border: OutlineInputBorder())),
                const SizedBox(height: 12),
                const TextField(
                  obscureText: true,
                  decoration: InputDecoration(labelText: 'Contraseña', border: OutlineInputBorder()),
                ),
                const SizedBox(height: 16),
                FilledButton(
                  onPressed: () => Navigator.pushReplacementNamed(context, '/home'),
                  child: const Text('Entrar'),
                ),
                TextButton(
                  onPressed: () => Navigator.pushNamed(context, '/register'),
                  child: const Text('¿No tienes cuenta? Regístrate'),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

class RegisterScreen extends StatelessWidget {
  const RegisterScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Registro')),
      body: Center(
        child: SingleChildScrollView(
          padding: const EdgeInsets.all(24),
          child: ConstrainedBox(
            constraints: const BoxConstraints(maxWidth: 420),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                const TextField(decoration: InputDecoration(labelText: 'Nombre', border: OutlineInputBorder())),
                const SizedBox(height: 12),
                const TextField(decoration: InputDecoration(labelText: 'Correo', border: OutlineInputBorder())),
                const SizedBox(height: 12),
                const TextField(
                  obscureText: true,
                  decoration: InputDecoration(labelText: 'Contraseña', border: OutlineInputBorder()),
                ),
                const SizedBox(height: 16),
                FilledButton(
                  onPressed: () => Navigator.pushNamedAndRemoveUntil(context, '/home', (_) => false),
                  child: const Text('Crear cuenta'),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return AppScaffold(
      title: 'Home',
      currentRoute: '/home',
      child: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(16),
            child: Container(
              height: 170,
              color: const Color(0xFFD9E6F2),
              alignment: Alignment.center,
              child: const Icon(Icons.house_rounded, size: 72, color: Color(0xFF1B263B)),
            ),
          ),
          const SizedBox(height: 16),
          Text('Panel principal', style: Theme.of(context).textTheme.headlineSmall),
          const SectionCard(
            title: 'Resumen de renta',
            description: 'Estado de pagos, contratos activos y notificaciones importantes.',
            icon: Icons.analytics_rounded,
          ),
          const SectionCard(
            title: 'Acciones rápidas',
            description: 'Crea propiedades, revisa solicitudes o abre el módulo de mantenimiento.',
            icon: Icons.flash_on_rounded,
          ),
        ],
      ),
    );
  }
}

class PropertiesScreen extends StatelessWidget {
  const PropertiesScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return AppScaffold(
      title: 'Propiedades',
      currentRoute: '/properties',
      actions: [
        IconButton(
          onPressed: () => Navigator.pushNamed(context, '/property-create'),
          icon: const Icon(Icons.add),
          tooltip: 'Crear propiedad',
        ),
      ],
      child: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          Text('Mis propiedades', style: Theme.of(context).textTheme.headlineSmall),
          const SectionCard(
            title: 'Casa San Miguel',
            description: '3 habitaciones · contrato activo · pago al día.',
            icon: Icons.home_work_rounded,
          ),
          Row(
            children: [
              Expanded(
                child: OutlinedButton(
                  onPressed: () => Navigator.pushNamed(context, '/property-detail'),
                  child: const Text('Ver detalle'),
                ),
              ),
              const SizedBox(width: 12),
              Expanded(
                child: FilledButton.tonal(
                  onPressed: () => Navigator.pushNamed(context, '/property-edit'),
                  child: const Text('Editar'),
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}

class PropertyCreateScreen extends StatelessWidget {
  const PropertyCreateScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const SimpleScreen(
      route: '/properties',
      title: 'Crear Propiedad',
      items: [
        ('Formulario base', 'Campos para dirección, renta, tipo y fotos.', Icons.add_home_work_rounded),
      ],
    );
  }
}

class PropertyDetailScreen extends StatelessWidget {
  const PropertyDetailScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const SimpleScreen(
      route: '/properties',
      title: 'Detalle de Propiedad',
      items: [
        ('Información del inmueble', 'Datos del contrato, ocupación y pagos.', Icons.home_rounded),
      ],
    );
  }
}

class PropertyEditScreen extends StatelessWidget {
  const PropertyEditScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const SimpleScreen(
      route: '/properties',
      title: 'Editar Propiedad',
      items: [
        ('Edición', 'Actualiza precio, estado y datos del arrendatario.', Icons.edit_rounded),
      ],
    );
  }
}

class AboutScreen extends StatelessWidget {
  const AboutScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const SimpleScreen(
      route: '/about',
      title: 'Nosotros',
      items: [
        ('Quiénes somos', 'Rentus digitaliza la administración de alquileres.', Icons.groups_rounded),
        ('Misión', 'Hacer más simple la vida de propietarios e inquilinos.', Icons.flag_rounded),
      ],
    );
  }
}

class ProfileScreen extends StatelessWidget {
  const ProfileScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const SimpleScreen(
      route: '/profile',
      title: 'Perfil',
      items: [
        ('Datos personales', 'Nombre, correo, teléfono y documentos.', Icons.person_rounded),
      ],
    );
  }
}

class NotificationsScreen extends StatelessWidget {
  const NotificationsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const SimpleScreen(
      route: '/notifications',
      title: 'Notificaciones',
      items: [
        ('Alertas', 'Vencimientos de pago, solicitudes y contratos.', Icons.notifications_active_rounded),
      ],
    );
  }
}

class ContractsScreen extends StatelessWidget {
  const ContractsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const SimpleScreen(
      route: '/contracts',
      title: 'Contratos',
      items: [
        ('Contratos activos', 'Consulta fechas, cláusulas y vigencia.', Icons.description_rounded),
      ],
    );
  }
}

class PaymentsScreen extends StatelessWidget {
  const PaymentsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const SimpleScreen(
      route: '/payments',
      title: 'Pagos',
      items: [
        ('Historial', 'Pagos recibidos, pendientes y próximos cobros.', Icons.payments_rounded),
      ],
    );
  }
}

class MaintenanceScreen extends StatelessWidget {
  const MaintenanceScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const SimpleScreen(
      route: '/maintenance',
      title: 'Mantenimiento',
      items: [
        ('Tickets', 'Solicitudes técnicas y seguimiento de trabajos.', Icons.build_rounded),
      ],
    );
  }
}

class MyRequestsScreen extends StatelessWidget {
  const MyRequestsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const SimpleScreen(
      route: '/my-requests',
      title: 'Mis Solicitudes',
      items: [
        ('Enviadas', 'Solicitudes creadas por ti y su estado actual.', Icons.assignment_ind_rounded),
      ],
    );
  }
}

class RequestsScreen extends StatelessWidget {
  const RequestsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const SimpleScreen(
      route: '/requests',
      title: 'Solicitudes',
      items: [
        ('Bandeja general', 'Solicitudes de inquilinos o propietarios.', Icons.assignment_rounded),
      ],
    );
  }
}

class MyReportsScreen extends StatelessWidget {
  const MyReportsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const SimpleScreen(
      route: '/my-reports',
      title: 'Mis Reportes',
      items: [
        ('Reportes', 'Indicadores de rentabilidad, mora y mantenimiento.', Icons.bar_chart_rounded),
      ],
    );
  }
}

class SettingsScreen extends StatelessWidget {
  const SettingsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const SimpleScreen(
      route: '/settings',
      title: 'Configuración',
      items: [
        ('Preferencias', 'Notificaciones, seguridad y ajustes de cuenta.', Icons.settings_rounded),
      ],
    );
  }
}
